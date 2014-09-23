CREATE TABLE IF NOT EXISTS "class" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "data_modelID" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "common" BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY ("data_modelID") REFERENCES "data_model"("id")
);

CREATE TABLE IF NOT EXISTS "attribute" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name" TEXT NOT NULL,
    "classID" INTEGER NOT NULL,
    FOREIGN KEY ("classID") REFERENCES "class"("id")
);

CREATE TABLE IF NOT EXISTS "data_model" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "collectionID" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    FOREIGN KEY ("collectionID") REFERENCES "collection"("id")    
);

CREATE TABLE IF NOT EXISTS "key" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "name" TEXT NOT NULL,
    "classID" INTEGER NOT NULL,
    "type" INTEGER NOT NULL,
    FOREIGN KEY("classID") REFERENCES "class"("id")
);

CREATE TABLE IF NOT EXISTS "key_attribute" (
    "keyID" INTEGER NOT NULL,
    "attributeID" INTEGER NOT NULL,
    "refersTo" INTEGER,
    FOREIGN KEY ("keyID") REFERENCES "key"("id"),
    FOREIGN KEY ("attributeID") REFERENCES "attribute"("id"),
    PRIMARY KEY ("keyID","attributeID")
);
