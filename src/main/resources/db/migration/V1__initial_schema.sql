-- V1__initial_schema.sql
-- Initial schema for Asset Guard Core

-- Users table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    employee_id VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255),
    role VARCHAR(50),
    record_status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Locations table (with self-reference)
CREATE TABLE locations (
    id UUID PRIMARY KEY,
    parent_location_id UUID REFERENCES locations(id),
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    record_status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);

-- Asset models table
CREATE TABLE asset_models (
    id UUID PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    model_name VARCHAR(255) NOT NULL,
    depreciation_years INTEGER NOT NULL,
    record_status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);

-- Assets table (with self-reference)
CREATE TABLE assets (
    id UUID PRIMARY KEY,
    parent_asset_id UUID REFERENCES assets(id),
    model_id UUID NOT NULL REFERENCES asset_models(id),
    asset_tag VARCHAR(255) NOT NULL UNIQUE,
    serial_number VARCHAR(255) UNIQUE,
    purchase_date DATE NOT NULL,
    purchase_cost DECIMAL(10,2) NOT NULL,
    residual_value DECIMAL(10,2) NOT NULL DEFAULT 0,
    lifecycle_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Asset location logs table
CREATE TABLE asset_location_logs (
    id UUID PRIMARY KEY,
    asset_id UUID NOT NULL REFERENCES assets(id),
    location_id UUID NOT NULL REFERENCES locations(id),
    transferred_by_id UUID NOT NULL REFERENCES users(id),
    arrival_date TIMESTAMP NOT NULL DEFAULT NOW(),
    departure_date TIMESTAMP
);

-- Custody logs table
CREATE TABLE custody_logs (
    id UUID PRIMARY KEY,
    asset_id UUID NOT NULL REFERENCES assets(id),
    assigned_by_id UUID NOT NULL REFERENCES users(id),
    custodian_id UUID NOT NULL REFERENCES users(id),
    checkout_date TIMESTAMP NOT NULL DEFAULT NOW(),
    checkout_condition VARCHAR(50) NOT NULL,
    acceptance_date TIMESTAMP,
    signature_hash VARCHAR(255),
    checkin_date TIMESTAMP,
    checkin_condition VARCHAR(50),
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING'
);

-- Maintenance work orders table
CREATE TABLE maintenance_work_orders (
    id UUID PRIMARY KEY,
    asset_id UUID NOT NULL REFERENCES assets(id),
    reported_by_id UUID NOT NULL REFERENCES users(id),
    technician_id UUID NOT NULL REFERENCES users(id),
    issue_description TEXT NOT NULL,
    intervention_cost DECIMAL(10,2) NOT NULL DEFAULT 0,
    start_date TIMESTAMP,
    completion_date TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Asset capitalizations table
CREATE TABLE asset_capitalizations (
    id UUID PRIMARY KEY,
    asset_id UUID NOT NULL REFERENCES assets(id),
    work_order_id UUID REFERENCES maintenance_work_orders(id),
    amount DECIMAL(10,2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    applied_date DATE NOT NULL
);

-- Indexes for common queries
CREATE INDEX idx_assets_model_id ON assets(model_id);
CREATE INDEX idx_assets_lifecycle_status ON assets(lifecycle_status);
CREATE INDEX idx_asset_location_logs_asset_id ON asset_location_logs(asset_id);
CREATE INDEX idx_custody_logs_asset_id ON custody_logs(asset_id);
CREATE INDEX idx_custody_logs_custodian_id ON custody_logs(custodian_id);
CREATE INDEX idx_maintenance_work_orders_asset_id ON maintenance_work_orders(asset_id);
CREATE INDEX idx_asset_capitalizations_asset_id ON asset_capitalizations(asset_id);
