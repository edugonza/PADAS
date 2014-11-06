CREATE TABLE IF NOT EXISTS "class" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name" TEXT NOT NULL,
    "common" BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS "attribute" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name" TEXT NOT NULL,
    "classID" INTEGER NOT NULL,
    FOREIGN KEY ("classID") REFERENCES "class"("id")
);

CREATE TABLE IF NOT EXISTS "attribute_value" (
    "eventID" INTEGER NOT NULL,
    "attributeID" INTEGER NOT NULL,
    "value" TEXT,
    FOREIGN KEY ("eventID") REFERENCES "event"("id"),
    FOREIGN KEY ("attributeID") REFERENCES "attribute"("id"),
    PRIMARY KEY ("eventID","attributeID")
);

CREATE TABLE IF NOT EXISTS "collection" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name" TEXT
);

CREATE TABLE IF NOT EXISTS "event" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "collectionID" INTEGER NOT NULL,
    FOREIGN KEY("collectionID") REFERENCES "collection"("id")
);

CREATE TABLE IF NOT EXISTS "perspective" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "name" TEXT NOT NULL,
    "collectionID" INTEGER NOT NULL,
    FOREIGN KEY ("collectionID") REFERENCES "collection"("id")
);

CREATE TABLE IF NOT EXISTS "trace" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "caseID" TEXT NOT NULL,
    "perspectiveID" INTEGER NOT NULL,
    FOREIGN KEY ("perspectiveID") REFERENCES "perspective"("id")
);

CREATE TABLE IF NOT EXISTS "trace_has_event" (
    "traceID" INTEGER NOT NULL,
    "eventID" INTEGER NOT NULL,
    "ordering" INTEGER NOT NULL,
    FOREIGN KEY ("traceID") REFERENCES "trace"("id"),
    FOREIGN KEY ("eventID") REFERENCES "event"("id"),
    PRIMARY KEY ("traceID","eventID")
);

CREATE TABLE IF NOT EXISTS "perspective_has_trace_id_pattern" (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "perspectiveID" INTEGER NOT NULL,
    "classID" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "type" INTEGER NOT NULL,
    FOREIGN KEY ("perspectiveID") REFERENCES "perspective"("id"),
    FOREIGN KEY ("classID") REFERENCES "class"("id")
);

CREATE TABLE IF NOT EXISTS "trace_has_value_trace_id_pattern" (
    "traceID" INTEGER NOT NULL,
    "perspectiveTraceIDPattern" INTEGER NOT NULL,
    "value" TEXT,
    FOREIGN KEY ("traceID") REFERENCES "trace"("id"),
    FOREIGN KEY ("perspectiveTraceIDPattern") REFERENCES "perspective_has_trace_id_pattern"("id"),
    PRIMARY KEY ("traceID","perspectiveTraceIDPattern")
);
