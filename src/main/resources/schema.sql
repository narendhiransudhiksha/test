create table IF NOT EXISTS player_account (player_id VARCHAR PRIMARY KEY, player_name VARCHAR, balance_amount NUMERIC(15,2),updated_date TIMESTAMP DEFAULT now(),update_by VARCHAR DEFAULT 'Admin' )

create table IF NOT EXISTS player_transaction (id INT PRIMARY KEY,  account_number VARCHAR REFERENCES  player_account(player_id),amount NUMERIC(15,2),transaction_date TIMESTAMP DEFAULT now(),transaction_type VARCHAR,transaction_id VARCHAR,reference_id VARCHAR,comment VARCHAR, status VARCHAR)

insert into player_account select * from (select 111, 'Dummy User',100,now(),'Admin' union select 100, 'test user 1',10000,now(),'Admin' union select 200, 'test user 2',10000,now(),'Admin' union select 300, 'test user 3',10000,now(),'Admin') x where not exists(select * from player_account)