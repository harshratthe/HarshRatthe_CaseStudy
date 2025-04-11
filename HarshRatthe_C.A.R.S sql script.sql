
DROP DATABASE IF EXISTS cars;
CREATE DATABASE cars;
USE cars;


DROP TABLE IF EXISTS Reports;
DROP TABLE IF EXISTS Evidence;
DROP TABLE IF EXISTS Incidents;
DROP TABLE IF EXISTS Suspects;
DROP TABLE IF EXISTS Victims;
DROP TABLE IF EXISTS Officers;
DROP TABLE IF EXISTS LawEnforcementAgency;


CREATE TABLE LawEnforcementAgency (
    agencyId INT PRIMARY KEY AUTO_INCREMENT,
    agencyName VARCHAR(100),
    contactInformation VARCHAR(100)
);


CREATE TABLE Officers (
    officerId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    badgeNumber VARCHAR(50),
    officerRank VARCHAR(50),
    contactInformation VARCHAR(100),
    agencyId INT,
    FOREIGN KEY (agencyId) REFERENCES LawEnforcementAgency(agencyId)
);


CREATE TABLE Victims (
    victimId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender ENUM('MALE', 'FEMALE'),
    address VARCHAR(200)
);


CREATE TABLE Suspects (
    suspectId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender ENUM('MALE', 'FEMALE'),
    phoneNumber VARCHAR(15)
);


CREATE TABLE Incidents (
    incidentId INT PRIMARY KEY AUTO_INCREMENT,
    incidentType VARCHAR(100),
    incidentDate DATE,
    location VARCHAR(200),
    description VARCHAR(300),
    status VARCHAR(50),
    victimId INT,
    suspectId INT,
    officerId INT,
    FOREIGN KEY (victimId) REFERENCES Victims(victimId),
    FOREIGN KEY (suspectId) REFERENCES Suspects(suspectId),
    FOREIGN KEY (officerId) REFERENCES Officers(officerId)
);


CREATE TABLE Evidence (
    evidenceId INT PRIMARY KEY AUTO_INCREMENT,
    evidenceType VARCHAR(100),
    collectedBy VARCHAR(100),
    collectedDate DATE,
    incidentId INT,
    description VARCHAR(300),
    FOREIGN KEY (incidentId) REFERENCES Incidents(incidentId)
);


CREATE TABLE Reports (
    reportId INT PRIMARY KEY AUTO_INCREMENT,
    incidentId INT NOT NULL,
    OfficerId INT NOT NULL,
    reportDate DATE NOT NULL,
    reportDetails VARCHAR(500),
    status VARCHAR(50),
    FOREIGN KEY (incidentId) REFERENCES Incidents(incidentId),
    FOREIGN KEY (OfficerId) REFERENCES Officers(officerId)
);

INSERT INTO LawEnforcementAgency (agencyName, contactInformation)
 VALUES
('Delhi Police', 'contact@delhipolice.in'),
('Mumbai Police', 'info@mumbaipolice.in'),
('Bangalore Police', 'help@blrpolice.in'),
('Hyderabad Police', 'contact@hydpolice.in'),
('Kolkata Police', 'support@kolpolice.in');


INSERT INTO Officers (firstName, lastName, badgeNumber, officerRank, contactInformation, agencyId)
 VALUES
('Raj', 'Verma', 'DP101', 'Inspector', 'raj.verma@delhipolice.in', 1),
('Amit', 'Shah', 'MP202', 'Sub-Inspector', 'amit.shah@mumbaipolice.in', 2),
('Neha', 'Rao', 'BP303', 'Constable', 'neha.rao@blrpolice.in', 3),
('Sanjay', 'Singh', 'HP404', 'Inspector', 'sanjay.singh@hydpolice.in', 4),
('Priya', 'Das', 'KP505', 'Constable', 'priya.das@kolpolice.in', 5);


INSERT INTO Victims (firstName, lastName, dateOfBirth, gender, address)
 VALUES
('Anita', 'Mehra', '1990-05-21', 'FEMALE', 'Connaught Place, Delhi'),
('Ravi', 'Kumar', '1985-12-10', 'MALE', 'Andheri West, Mumbai'),
('Sonal', 'Jain', '1993-08-05', 'FEMALE', 'Koramangala, Bangalore'),
('Arjun', 'Singh', '1980-03-15', 'MALE', 'Banjara Hills, Hyderabad'),
('Meera', 'Iyer', '1995-11-20', 'FEMALE', 'Salt Lake, Kolkata'),
('Karan', 'Joshi', '1987-07-12', 'MALE', 'Dwarka, Delhi'),
('Pooja', 'Patel', '1992-04-01', 'FEMALE', 'Powai, Mumbai'),
('Ramesh', 'Rao', '1984-09-25', 'MALE', 'Whitefield, Bangalore'),
('Geeta', 'Nair', '1991-06-18', 'FEMALE', 'Gachibowli, Hyderabad'),
('Suresh', 'Das', '1978-02-14', 'MALE', 'Behala, Kolkata');


INSERT INTO Suspects (firstName, lastName, dateOfBirth, gender, phoneNumber) 
VALUES
('Vikram', 'Singh', '1982-01-12', 'MALE', '9876543210'),
('Alok', 'Verma', '1989-03-05', 'MALE', '9876543211'),
('Sanjana', 'Reddy', '1990-07-07', 'FEMALE', '9876543212'),
('Deepak', 'Thakur', '1985-09-09', 'MALE', '9876543213'),
('Kavita', 'Pillai', '1994-06-06', 'FEMALE', '9876543214'),
('Ajay', 'Yadav', '1983-11-11', 'MALE', '9876543215'),
('Namita', 'Sharma', '1991-01-01', 'FEMALE', '9876543216'),
('Harish', 'Mehta', '1986-10-10', 'MALE', '9876543217'),
('Neelam', 'Desai', '1988-08-08', 'FEMALE', '9876543218'),
('Rajiv', 'Kapoor', '1979-04-04', 'MALE', '9876543219');


INSERT INTO Incidents (incidentType, incidentDate, location, description, status, victimId, suspectId, officerId)
 VALUES
('Theft', '2024-04-01', 'Delhi', 'Stolen purse at market', 'Filed', 1, 1, 1),
('Assault', '2024-04-02', 'Mumbai', 'Physical altercation', 'Under Investigation', 2, 2, 2),
('Fraud', '2024-04-03', 'Bangalore', 'Bank scam reported', 'Filed', 3, 3, 3),
('Snatching', '2024-04-04', 'Mumbai CST', 'Chain snatching in station area', 'Closed', 4, 4, 1),
('Hit and Run', '2024-04-05', 'Pune', 'Unknown vehicle fled scene', 'Filed', 5, 5, 4),
('Domestic Abuse', '2024-04-06', 'Hyderabad', 'Reported by neighbor', 'Draft', 6, 6, 2),
('Vandalism', '2024-04-07', 'Chennai', 'Public property damaged', 'Filed', 7, 7, 3),
('Cybercrime', '2024-04-08', 'Noida', 'Phishing site complaint', 'Under Review', 8, 8, 5),
('Attempted Murder', '2024-04-09', 'Delhi', 'Knife attack in park', 'Filed', 9, 9, 1),
('Drug Possession', '2024-04-10', 'Goa', 'Narcotics found on suspect', 'Filed', 10, 10, 4);


INSERT INTO Reports (incidentId, officerId, reportDate, reportDetails, status)
 VALUES
(1, 1, '2024-04-01', 'Report regarding theft case in Delhi, officer inspected location and recorded evidence.', 'Filed'),
(2, 2, '2024-04-02', 'Report submitted for assault case in Mumbai, statements collected.', 'Filed'),
(3, 3, '2024-04-03', 'Investigated fraud complaint in Bangalore, documents verified.', 'Under Investigation'),
(4, 1, '2024-04-04', 'Follow-up report for chain snatching near CST station.', 'Closed'),
(5, 4, '2024-04-05', 'Report for hit-and-run case in Pune, victim hospitalised.', 'Filed'),
(6, 2, '2024-04-06', 'Domestic violence case report in Hyderabad.', 'Draft'),
(7, 3, '2024-04-07', 'Vandalism complaint near Chennai Marina Beach, filed for investigation.', 'Filed'),
(8, 5, '2024-04-08', 'Report for cybercrime phishing attempt in Noida.', 'Under Review'),
(9, 1, '2024-04-09', 'Attempt to murder case report near Lajpat Nagar, witness statements collected.', 'Filed'),
(10, 4, '2024-04-10', 'Drug possession case investigated in Goa, samples seized.', 'Filed');

INSERT INTO Evidence (evidenceType, collectedBy, collectedDate, incidentId, description) 
VALUES
('Photograph', 'Raj Verma', '2024-04-01', 1, 'Image of the stolen purse area from CCTV footage'),
('Weapon', 'Amit Shah', '2024-04-02', 2, 'Recovered stick used in assault'),
('Documents', 'Neha Rao', '2024-04-03', 3, 'Bank statements indicating fraudulent transactions'),
('CCTV Footage', 'Raj Verma', '2024-04-04', 4, 'Footage of chain snatching near station'),
('Vehicle Debris', 'Sanjay Singh', '2024-04-05', 5, 'Broken headlight glass from hit-and-run case'),
('Statement', 'Amit Shah', '2024-04-06', 6, 'Victim neighbor statement in domestic abuse case'),
('Spray Can', 'Neha Rao', '2024-04-07', 7, 'Used paint spray can found near vandalized wall'),
('Laptop', 'Priya Das', '2024-04-08', 8, 'Seized laptop used in cyber phishing'),
('Knife', 'Raj Verma', '2024-04-09', 9, 'Blood-stained knife recovered from crime scene'),
('Packet of Drugs', 'Sanjay Singh', '2024-04-10', 10, 'Sealed packet containing narcotic substance');

