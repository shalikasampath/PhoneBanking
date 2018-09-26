create table customers(mobile_no varchar2(10) NOT NULL,fname varchar2(10),lname varchar2(10),PRIMARY KEY(mobile_no));
create table savings(mobile_no varchar2(10) NOT NULL,Balance varchar2(10),Interest_rate varchar2(10),TR1 varchar(30),TR2 varchar(30),TR3 varchar2(30),PRIMARY KEY(mobile_no),FOREIGN KEY(mobile_no) references customers(mobile_no));
create table credit(mobile_no varchar2(10) NOT NULL,Due_Amt int,Due_Date varchar(10),limit varchar2(10),min_payble_amt varchar2(10),Interest_Rate varchar2(10),TR1 varchar2(30),TR2 varchar2(30),TR3 varchar2(30),PRIMARY KEY(mobile_no),FOREIGN KEY(mobile_no) references customers(mobile_no));
create table loan(mobile_no varchar2(10) NOT NULL,Pending_Amt varchar2(10),Emi varchar2(10),Emi_Due_Date varchar(10),Principal_amt varchar2(10),Interest_Rate varchar2(10),PRIMARY KEY(mobile_no),FOREIGN KEY(mobile_no) references customers(mobile_no));
insert into customers values(9741234316,'Alice','Mane');
insert into customers values(8123456342,'Ravi','Kumar');
insert into customers values(8789514210,'Brittany','Ross');
insert into customers values(8789514329,'John','Ross');
insert into customers values(7895456342,'Frank','cameron');
insert into savings values(9741234316,65000,4,'5000 credit 24-09-2017','3000 debit 26-09-2017','4000 credit 12-10-2017');
insert into savings values(8123456342,75000,4,'4500 credit 28-09-2017','4000 credit 26-10-2017','8000 debit 22-10-2017');
insert into savings values(7895456342,70000,4,'3500 debit 22-11-2017','4000 credit 29-11-2017','12000 debit 22-12-2017');
insert into savings values(8789514210,90000,4,'5500 credit 21-10-2017','6000 credit 12-11-2017','12000 debit 22-12-2017');
insert into savings values(8789514329,45000,4,'2500 credit 12-12-2017','6000 credit 15-12-2017','2000 debit 22-12-2017');
insert into credit values(9741234316,12000,'23-08-2017',40000,2500,3.5,'3000 12-09-2017','5000 18-09-2017','28-09-2017');
insert into credit values(8789514210,20000,'13-10-2017',50000,6500,3.5,'5000 11-09-2017','5000 17-09-2017 4000','10000 02-10-2017');
insert into loan values(8123456342,80000,4600,'13-11-2017',250000,8.5);
insert into loan values(8789514329,120000,6500,'06-12-2017',400000,8.5);



