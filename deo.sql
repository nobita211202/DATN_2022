WITH bspm AS (
    SELECT
        bspm.sum_date,
        bn_reg_id,
        subs_pkg_id,
        COUNT(DISTINCT subs_id) subs_count,
        SUM(prv_part_count)     prv_part_count
    FROM
        bn_subs_pkg_monthly bspm
    WHERE
            bspm.sum_date >= TO_DATE('06-2022', 'MM-yyyy')
        AND bspm.sum_date < add_months(TO_DATE('06-2022', 'MM-yyyy'), 1)
    GROUP BY
        bspm.sum_date,
        bn_reg_id,
        subs_pkg_id
), dat AS (
    SELECT
        bspm.*,
        c.client_id,
        c.client_type_id,
        f.code                                                                                              f_code,
        f.name                                                                                              f_name,
        bn.code                                                                                             bn_code,
        sp.code                                                                                             sp_code,
        determine_subs_tariff_detail_id(bspm.sum_date, c.client_type_id, bspm.subs_pkg_id, bspm.subs_count) subs_tariff_detail_id
    FROM
        bspm,
        client     c,
        subs_pkg   sp,
        brand_name bn,
        bn_reg     br,
        field      f
    WHERE
            c.client_id = 932
        AND br.bn_reg_id = bspm.bn_reg_id
        AND sp.subs_pkg_id = bspm.subs_pkg_id
        AND br.bn_id = bn.bn_id
        AND f.field_id = br.field_id
        AND br.client_id = c.client_id
)
SELECT
    'SUBS',
    f_code
    || ' '
    || f_name,
    bn_code,
    sp_code,
    (
        CASE
            WHEN td.subs_pkg_id = 1 THEN
                'SUBS_KHCN'
            ELSE
                'SUBS_KHDN'
        END
    ),
    td.subs_amount_from
    || ' - '
    || td.subs_amount_to,
    td.price,
    SUM(dat.prv_part_count),
    SUM(dat.prv_part_count) * td.price,
    SUM(dat.subs_count),
    GROUPING('SUBS'),
    GROUPING(f_code
             || ' '
             || f_name),
    GROUPING(bn_code),
    GROUPING(sp_code),
    GROUPING((
        CASE
            WHEN td.subs_pkg_id = 1 THEN
                'SUBS_KHCN'
            ELSE
                'SUBS_KHDN'
        END
    )),
    GROUPING(td.subs_amount_from
             || ' - '
             || td.subs_amount_to),
    GROUPING(td.price)
FROM
    dat,
    subs_tariff_detail td
WHERE
    td.subs_tariff_detail_id = dat.subs_tariff_detail_id (+)
GROUP BY
    CUBE('SUBS',
         f_code
         || ' '
         || f_name,
         bn_code,
         sp_code,
         (
             CASE
                 WHEN td.subs_pkg_id = 1 THEN
                     'SUBS_KHCN'
                 ELSE
                     'SUBS_KHDN'
             END
         ),
         td.subs_amount_from
         || ' - '
         || td.subs_amount_to,
         td.price)
UNION ALL
(
    SELECT
        'ALL',
        f.code
        || ''
        || f.name,
        bn.code,
        'code',
        td.quantity_from
        || '-'
        || td.quantity_to,
        'ALL',
        td.price,
        SUM(amm.prv_part_count),
        SUM(amm.prv_part_count) * td.price,
        NULL,
        GROUPING('ALL'),
        GROUPING(f.code
                 || ''
                 || f.name),
        GROUPING(bn.code),
        GROUPING('code'),
        GROUPING(td.quantity_from
                 || '-'
                 || td.quantity_to),
        GROUPING('ALL'),
        GROUPING(td.price)
    FROM
        acc_mt_monthly amm,
        brand_name     bn,
        acc_bn         ab,
        field          f,
        tariff_detail  td,
        account        acc,
        bn_reg         br,
        client         c
    WHERE
            amm.acc_id = acc.acc_id
        AND ab.acc_bn_id = amm.acc_bn_id
        AND bn.bn_id = amm.bn_id
        AND td.field_id = f.field_id
        AND br.bn_id = bn.bn_id
        AND ab.bn_reg_id = br.bn_reg_id
        AND c.client_id = acc.client_id
        AND sum_date = TO_DATE('06-2022', 'MM/yyyy')
        AND c.client_id = 932
    GROUP BY
        CUBE('ALL',
             f.code
             || ''
             || f.name,
             '',
             bn.code,
             '',
             td.quantity_from
             || '-'
             || td.quantity_to,
             amm.sum_date,
             bn.code,
             td.price,
             'code')
)