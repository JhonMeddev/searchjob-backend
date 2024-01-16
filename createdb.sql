-- init-db.sql
ALTER USER postgres WITH PASSWORD 'jhonatan';
DO $$ BEGIN
  IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'searchjobs') THEN
    CREATE DATABASE searchjobs;
  END IF;
END $$;
