-- Drop the tables if they exist
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

-- Create the users table
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  profile VARCHAR(255) NOT NULL,
  type INT NOT NULL DEFAULT '1',
  phone VARCHAR(20),
  address VARCHAR(255),
  dob DATE,
  created_user_id INT NOT NULL,
  updated_user_id INT NOT NULL,
  deleted_user_id INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  deleted_at TIMESTAMP
);

-- Create the posts table
CREATE TABLE posts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  status INT DEFAULT 1,
  created_user_id INT NOT NULL,
  updated_user_id INT NOT NULL,
  deleted_user_id INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  deleted_at TIMESTAMP,
  FOREIGN KEY (created_user_id) REFERENCES users(id),
  FOREIGN KEY (updated_user_id) REFERENCES users(id),
  FOREIGN KEY (deleted_user_id) REFERENCES users(id)
);

-- Insert records into the users table
INSERT INTO users (name, email, password, profile, type, phone, address, dob, created_user_id, updated_user_id, created_at, updated_at)
VALUES
  ('John Doe', 'john.doe@example.com', 'password123', 'profile1.jpg', 1, '1234567890', '123 Street, City', '1990-01-01', 1, 1, '2023-07-17 08:56:16', '2023-07-17 08:56:16'),
  ('Jane Smith', 'jane.smith@example.com', 'password456', 'profile2.jpg', 1, '0987654321', '456 Avenue, Town', '1995-05-10', 1, 1, '2023-07-17 08:56:16' , '2023-07-17 08:56:16'),
  ('Michael Johnson', 'michael.johnson@example.com', 'password789', 'profile3.jpg', 0, '9876543210', '789 Road, Village', '1985-09-15', 1, 1, '2023-07-17 08:56:16', '2023-07-17 08:56:16');

-- Insert records into the posts table
INSERT INTO posts (title, description, status, created_user_id, updated_user_id, created_at, updated_at)
VALUES
  ('Post 1', 'Description for Post 1', 1, 1, 1, '2023-07-17 08:56:16', '2023-07-17 08:56:16'),
  ('Post 2', 'Description for Post 2', 1, 1, 1, '2023-07-17 08:56:16', '2023-07-17 08:56:16'),
  ('Post 3', 'Description for Post 3', 0, 1, 1, '2023-07-17 08:56:16', '2023-07-17 08:56:16');
