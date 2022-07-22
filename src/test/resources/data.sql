DELETE FROM quote;
DELETE FROM stock;

INSERT INTO stock(id, stock_id) VALUES('123','test4');
INSERT INTO stock(id, stock_id) VALUES('1234','test5');


INSERT INTO quote(id, data_quote, valor_quote, stock_id) VALUES('12345','2019-01-01', 16, '123');
INSERT INTO quote(id, data_quote, valor_quote, stock_id) VALUES('123456','2019-01-02', 15, '123');


INSERT INTO quote(id, data_quote, valor_quote, stock_id) VALUES('1234567','2019-01-01', 13, '1234');
