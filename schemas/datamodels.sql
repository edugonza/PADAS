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
    "collectionID" INTEGER NOT NULL,
    FOREIGN KEY ("collectionID") REFERENCES "collection"("id")
);

CREATE TABLE IF NOT EXISTS "data_model" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "collectionID" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    FOREIGN KEY ("collectionID") REFERENCES "collection"("id")    
);

