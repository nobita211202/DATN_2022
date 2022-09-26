 CREATE OR REPLACE PROCEDURE RPT_QUANTITY_DAILY
(
	report_time date
)LANGUAGE plpgsql
AS $$
	BEGIN
	DECLARE arow record;
	BEGIN
	 FOR arow IN 
	 (
		SELECT *
			FROM (
				SELECT DISTINCT order_.user_id as user_id,category_attr_.category_id 
					as category_id,order_.course_id as course_id,
					order_.voucher_id as voucher_id,order_.order_id as order_id,payment_method.name_ as payment_method,
					to_char(order_.created,'yyyy-MM-dd')as report_date FROM Orders order_ 
								JOIN Users user_ on order_.user_id = user_.user_id
								JOIN COURSES course_ on course_.course_id = order_.course_id
								JOIN CATEGORIES_ATTR category_attr_ on category_attr_.category_attr_id=course_.category_attr_id
								JOIN VOUCHERS voucher_ on voucher_.voucher_id=order_.voucher_id
								JOIN Payments payment_ on payment_.order_id = order_.order_id
								JOIN payment_method ON payment_method.payment_method_id = payment_.payment_method_id
								WHERE order_.status in (1,2)
										and to_char(order_.created,'dd/MM/yyyy')a=to_char(report_time,'dd/MM/yyyy')
										and user_.status= 0
										and category_attr_.status= 0 
										and course_.status = 0
				) as tempTable
	 )
		loop
	 INSERT INTO REPORT_QUANTITY_DAILY values(arow.user_id,arow.category_id,arow.course_id,arow.voucher_id,arow.order_id,arow.payment_method,cast(arow.report_date as date));
	end loop;
	END;
END;$$;
---call
CALL RPT_QUANTITY_DAILY('2022-08-31');
-----------------

 CREATE OR REPLACE PROCEDURE RPT_QUANTITY_MONTHLY
(
	report_time date
)LANGUAGE plpgsql
AS $$
	BEGIN
	DECLARE arow record;
	BEGIN
	 FOR arow IN 
	 (
		  SELECT *
		FROM (
			SELECT DISTINCT order_.user_id as user_id,category_attr_.category_id 
			as category_id,order_.course_id as course_id,
			order_.voucher_id as voucher_id,order_.order_id as order_id,payment_method.name_ as payment_method,
			to_char(order_.created,'yyyy-MM-dd')as report_date FROM Orders order_ 
							JOIN Users user_ on order_.user_id = user_.user_id
							JOIN COURSES course_ on course_.course_id = order_.course_id
							JOIN CATEGORIES_ATTR category_attr_ on category_attr_.category_attr_id=course_.category_attr_id
							JOIN VOUCHERS voucher_ on voucher_.voucher_id=order_.voucher_id
							JOIN Payments payment_ on payment_.order_id = order_.order_id
							JOIN payment_method ON payment_method.payment_method_id = payment_.payment_method_id
							WHERE order_.status in (1,2)
									and to_char(order_.created,'MM/yyyy') =to_char(report_time,'MM/yyyy')
									and user_.status= 0
									and category_attr_.status= 0 
									and course_.status = 0
		) as tempTable
	 )
		loop
	 INSERT INTO REPORT_QUANTITY_MONTHLY values(arow.user_id,arow.category_id,arow.course_id,arow.voucher_id,arow.order_id,arow.payment_method,cast(arow.report_date as date));
	end loop;
	END;
END;$$;
---call
CALL RPT_QUANTITY_MONTHLY(cast('2020-08' as date))
