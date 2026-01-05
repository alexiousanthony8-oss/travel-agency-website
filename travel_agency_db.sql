-- Create Database
CREATE DATABASE IF NOT EXISTS travel_agency_db;
USE travel_agency_db;

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(15),
    address VARCHAR(255),
    city VARCHAR(50),
    country VARCHAR(50),
    role ENUM('user', 'admin') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Destinations Table
CREATE TABLE destinations (
    destination_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    price_per_day DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    image_url VARCHAR(255),
    highlights TEXT,
    best_season VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bookings Table
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    destination_id INT NOT NULL,
    number_of_people INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    status ENUM('pending', 'confirmed', 'cancelled') DEFAULT 'pending',
    special_requests TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (destination_id) REFERENCES destinations(destination_id)
);

-- Inquiries Table (for contact form)
CREATE TABLE inquiries (
    inquiry_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    status ENUM('new', 'replied', 'resolved') DEFAULT 'new',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Index for better query performance
CREATE INDEX idx_user_username ON users(username);
CREATE INDEX idx_destination_country ON destinations(country);
CREATE INDEX idx_booking_user_id ON bookings(user_id);
CREATE INDEX idx_booking_destination_id ON bookings(destination_id);
CREATE INDEX idx_inquiry_email ON inquiries(email);

-- Insert Sample Data
INSERT INTO users (username, email, password, first_name, last_name, phone, address, city, country, role) VALUES
('john_doe', 'john@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/D0K', 'John', 'Doe', '555-0001', '123 Main St', 'New York', 'USA', 'user'),
('jane_smith', 'jane@example. com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/D0K', 'Jane', 'Smith', '555-0002', '456 Oak Ave', 'Los Angeles', 'USA', 'user'),
('admin_user', 'admin@travelagency.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/D0K', 'Admin', 'User', '555-9999', '789 Admin Ln', 'Boston', 'USA', 'admin');

INSERT INTO destinations (name, description, country, city, price_per_day, duration_days, image_url, highlights, best_season) VALUES
('Paris Romance', 'Experience the magic of Paris with iconic landmarks and romantic cafes', 'France', 'Paris', 150.00, 5, '/images/paris.jpg', 'Eiffel Tower, Louvre Museum, Seine River Cruise', 'April-May, September-October'),
('Tokyo Adventure', 'Explore the vibrant culture and modern technology of Tokyo', 'Japan', 'Tokyo', 120.00, 4, '/images/tokyo.jpg', 'Senso-ji Temple, Shibuya Crossing, Mount Fuji Day Trip', 'March-May, September-November'),
('New York Experience', 'The city that never sleeps awaits your exploration', 'USA', 'New York', 180.00, 3, '/images/newyork.jpg', 'Times Square, Broadway, Central Park, Statue of Liberty', 'May-June, September-October'),
('Bali Relaxation', 'Tropical paradise with beautiful beaches and ancient temples', 'Indonesia', 'Denpasar', 80.00, 7, '/images/bali.jpg', 'Ubud Rice Terraces, Bali Beaches, Hindu Temples', 'April-June, September-October'),
('London History', 'Discover centuries of history in the heart of England', 'UK', 'London', 140.00, 4, '/images/london.jpg', 'Big Ben, Tower of London, British Museum', 'May-June, September-October');

-- Password for all sample users:  password123
