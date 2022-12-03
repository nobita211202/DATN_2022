
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