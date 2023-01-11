
CREATE OR REPLACE PROCEDURE user_order(
	user_id_param BIGINT,
	course_id BIGINT[]
)LANGUAGE plpgsql
AS $$
	BEGIN
		DECLARE
			code 		 TEXT 	   := md5(random()::text) || '_' || to_char(CURRENT_DATE,'ddMMyyyy');
			created 	 TIMESTAMP := CURRENT_TIMESTAMP ;
			effect_from  TIMESTAMP := CURRENT_TIMESTAMP ;
			effect_until TIMESTAMP := CURRENT_TIMESTAMP + INTERVAL '7 day' ;
			status 		 BIGINT    := 0;
			arow		 BIGINT;
			creator 	 TEXT	   := (SELECT username FROM USERS WHERE  user_id = user_id_param);
		BEGIN
			IF user_id_param IS NULL THEN
				RAISE EXCEPTION 'User id cannot be null';
			END IF;
			IF course_id IS NULL THEN
				RAISE EXCEPTION 'Courseid cannot be null';
			END IF;
			INSERT INTO ORDERS(
							code,user_id,effect_from,
							effect_until,created,creator,modified,
							modifier,status
				)
				VALUES(
						code,user_id_param,effect_from,effect_until
					   ,created,creator,created,'',
					   status
				);
				COMMIT;
			BEGIN
				FOREACH arow IN ARRAY course_id
				LOOP
					DECLARE
						price_temp REAL :=  (SELECT s.price FROM COURSES s WHERE s.course_id =arow);
						order_id_temp BIGINT := (SELECT MAX(o.order_id) FROM ORDERS o WHERE o.user_id =user_id_param);
					BEGIN
							INSERT INTO ORDERS_DETAIL(
								created,price,
								status,course_id,
								order_id
							)
							VALUES(
								created,
								price_temp,
								0,
								arow,
								order_id_temp
							);
							COMMIT;
						END;
				END LOOP;
			END;
		END ;
	END;$$








-----------
VIEW ORDERR BY ID
----------




CREATE OR REPLACE FUNCTION view_order_detail_by_userId(
	user_id_param BIGINT
)
RETURNS TABLE(
	code varchar,
	created text,
	name_	text,
	price real
)
AS $$
		DECLARE
			user_id_ BIGINT := user_id_param;
			BEGIN
			RETURN QUERY
				SELECT o.code,to_char(od.created,'dd/MM/yyyy hh:mm:ss') AS created,c_.name_,od.price
					FROM ORDERS o LEFT OUTER JOIN ORDERS_DETAIL od
						ON o.order_id = od.order_id
					INNER JOIN COURSES c_
						ON c_.course_id = od.course_id
					JOIN categories_attr ca
						ON ca.category_attr_id = c_.category_attr_id
					WHERE o.user_id = user_id_ ;
				END;
	$$ LANGUAGE plpgsql;

-------- DELETE ORDER DETAIL

CREATE OR REPLACE PROCEDURE delete_orderDetail_from_order(
	order_detail_id_ BIGINT
)LANGUAGE plpgsql
AS $$
BEGIN
	IF order_detail_id_ IS NULL THEN
		RAISE EXCEPTION 'Order detail id is not null';
	END IF;
	DECLARE
		is_exist_order_detail_id BIGINT := ( SELECT od_.order_detail_id
									 FROM
									 orders_detail od_
									 WHERE od_.order_detail_id =order_detail_id_
									);
	BEGIN
		IF is_exist_order_detail_id IS NULL THEN
			RAISE EXCEPTION 'Order detail id is not exist';
		END IF;
		DELETE FROM orders_detail WHERE order_detail_id = order_detail_id_;
		COMMIT;
	END;
END $$;
--------Thanh toán
CREATE OR REPLACE PROCEDURE update_status_pay_success(
	order_code_ VARCHAR
)LANGUAGE plpgsql
AS $$
BEGIN
	DECLARE
		is_exist_code VARCHAR(100) := (SELECT code FROM orders WHERE code = order_code_);
		ele record;
	BEGIN
		IF is_exist_code IS NULL THEN
			RAISE EXCEPTION 'Code (%) not found',order_code_;
		END IF;
		FOR ele IN (
			SELECT * FROM orders WHERE code = order_code_
		)LOOP
			IF ele.status = 1 THEN
				RAISE EXCEPTION 'Order % has been paid',order_code_;
			END IF;
			IF ele.effect_until < CURRENT_TIMESTAMP THEN
				RAISE EXCEPTION 'order is experied';
			END IF;
			IF ele.voucher_id IS NOT NULL THEN
				UPDATE orders SET price = (
					SELECT price FROM orders WHERE code = ele.code
				) - (
					SELECT discount FROM vouchers WHERE voucher_id = ele.voucher_id
				) WHERE code = ele.code;
					IF NOT FOUND THEN
						ROLLBACK;
						RAISE EXCEPTION 'Error when update price';
					ELSE
						COMMIT;
					END IF;
			END IF;
			UPDATE orders SET status = 1 WHERE code = ele.code;
		END LOOP;
	END;
END $$;
