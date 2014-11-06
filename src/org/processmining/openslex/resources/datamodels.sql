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
    "name" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "key" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "name" TEXT NOT NULL,
    "classID" INTEGER NOT NULL,
    "type" INTEGER NOT NULL,
    "refers_to_key" INTEGER,
    FOREIGN KEY("classID") REFERENCES "class"("id"),
    FOREIGN KEY("refers_to_key") REFERENCES "key"("id")
);

CREATE TABLE IF NOT EXISTS "key_attribute" (
    "keyID" INTEGER NOT NULL,
    "attributeID" INTEGER NOT NULL,
    "refersTo" INTEGER,
    "position" INTEGER,
    FOREIGN KEY ("keyID") REFERENCES "key"("id"),
    FOREIGN KEY ("attributeID") REFERENCES "attribute"("id"),
    FOREIGN KEY ("refersTo") REFERENCES "attribute"("id"),
    PRIMARY KEY ("keyID","attributeID")
);
