
CREATE TABLE Societe (
  societeId INT PRIMARY KEY,
  adresse VARCHAR(50),
  siteWeb VARCHAR(50),
  nCCP INT
);

CREATE TABLE nTlphs (
  tlphsid INT PRIMARY KEY,
  societeId INT,
  nTlph INT,
  FOREIGN KEY (societeId) REFERENCES Societe(societeId)
);


CREATE TABLE Catalogue (
  nCatalogue INT NOT NULL PRIMARY KEY,
  nomCatalogue VARCHAR(50) NOT NULL,
  societeId INT NOT NULL,
  FOREIGN KEY (societeId) REFERENCES Societe(societeId)
);

CREATE TABLE Employee (
  codeEmployee INT PRIMARY KEY,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  dateNaiss Date,
  adrPer VARCHAR(50),
  sect VARCHAR(50),
  anciennite INT,
  nCatalogue INT,
  FOREIGN KEY (nCatalogue) REFERENCES Catalogue(nCatalogue)
);

CREATE TABLE Medcin (
  codeEmployee INT NOT NULL,
  specialite VARCHAR(50),
  PRIMARY KEY (codeEmployee),
  FOREIGN KEY (codeEmployee) REFERENCES Employee(codeEmployee)
);

CREATE TABLE Patient (
  nSS INT NOT NULL,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  dateNaiss DATE NOT NULL,
  adrPer VARCHAR(255) NOT NULL,
  nTlphPat INT NOT NULL,
  adrMail VARCHAR(255) NOT NULL,
  PRIMARY KEY (nSS)
);



CREATE TABLE Reservation (
  nReservation INT NOT NULL,
  serviceDem VARCHAR(255),
  typeRes VARCHAR(255),
  dateres DATE,
  nSS INT,
  PRIMARY KEY (nReservation),
  FOREIGN KEY (nSS) REFERENCES Patient(nSS)
);

CREATE TABLE Consultation (
  consulID INT PRIMARY KEY AUTO_INCREMENT,
  nReservation INT NOT NULL,
  nSS INT NOT NULL,
  codeEmployee INT NOT NULL,
  resultat VARCHAR(255),
  recommendation VARCHAR(255),
  etatPat VARCHAR(255),
  /*FOREIGN KEY (nReservation) REFERENCES Reservation(nReservation),*/
  FOREIGN KEY (nSS) REFERENCES Patient(nSS),
  FOREIGN KEY (codeEmployee) REFERENCES Employee(codeEmployee)
);

CREATE TABLE ConsultationMed (
  consulID INT,
  traitementDonne VARCHAR(255),
  PRIMARY KEY (consulID),
  FOREIGN KEY (consulID) REFERENCES Consultation(consulID)
);

CREATE TABLE ConsultationParamed (
  consulID INT,
  traitementEff VARCHAR(255),
  PRIMARY KEY (consulID),
  FOREIGN KEY (consulID) REFERENCES Consultation(consulID)
);

CREATE TABLE Feedback (
  feedbackID INT NOT NULL AUTO_INCREMENT,
  commentaire VARCHAR(50),
  nbEtService INT NOT NULL,
  nbEtPonct INT NOT NULL,
  nbEtTraitement INT NOT NULL,
  consulID INT NOT NULL,
  PRIMARY KEY (feedbackID),
  FOREIGN KEY (consulID) REFERENCES Consultation(consulID)
);


