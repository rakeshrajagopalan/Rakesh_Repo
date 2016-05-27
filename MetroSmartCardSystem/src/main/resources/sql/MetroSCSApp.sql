SET AUTOCOMMIT = 0;
create schema rakesh;
use rakesh;

Create Table Station(
StationId Integer AUTO_INCREMENT Primary Key Not Null,
StationName Varchar(1000) Not Null,
StationOrder Integer Not Null);

Insert Into Station(StationName,StationOrder) Values('A1',1);
Insert Into Station(StationName,StationOrder) Values('A2',2);
Insert Into Station(StationName,StationOrder) Values('A3',3);
Insert Into Station(StationName,StationOrder) Values('A4',4);
Insert Into Station(StationName,StationOrder) Values('A5',5);
Insert Into Station(StationName,StationOrder) Values('A6',6);
Insert Into Station(StationName,StationOrder) Values('A7',7);
Insert Into Station(StationName,StationOrder) Values('A8',8);
Insert Into Station(StationName,StationOrder) Values('A9',9);
Insert Into Station(StationName,StationOrder) Values('A10',10);

Create Table Card(
CardId Integer AUTO_INCREMENT Primary Key Not Null,
CardHolderName Varchar(200) Not Null,
CardHolderIdentityType Varchar(200) Not Null,
CardHolderIdentityValue Varchar(200) Not Null,
CardBalance Float(5,2),
Blocked Char(1) Default '0');

Insert Into Card(CardHolderName,CardBalance,CardHolderIdentityType,CardHolderIdentityValue) Values ('Rakesh',50.00,'Passport','ABC123');
Insert Into Card(CardHolderName,CardBalance,CardHolderIdentityType,CardHolderIdentityValue) Values ('Sruti',50.00,'Passport','ABD123');

Create Table Travel(
TravelId Integer AUTO_INCREMENT Primary Key Not Null,
CardId Integer  Not Null,
FromStation Integer Not Null,
ToStation Integer Not Null,
StartTime Timestamp,
EndTime Timestamp,
BaseCharge Float(5,2),
TravelCharges Float(5,2),
Foreign Key (CardId) References Card(CardId),
Foreign Key (FromStation) References Station(StationId),
Foreign Key (ToStation) References Station(StationId)
);
Commit;