# ToysHibernate

ToysHibernate is a Java project that utilizes Hibernate 6.3.1 to manage a MySQL database for handling toy-related data. Unlike traditional XML configuration, this project employs a HibernateConfig class to configure the Hibernate setup. Additionally, a singleton pattern is implemented through the HibernateUtil class, which returns an instance of the Hibernate connection.

## Features

- **Hibernate 6.3.1**: Utilizes the latest version of Hibernate for efficient and powerful database operations.

- **HibernateConfig Class**: Centralizes the configuration of Hibernate, replacing the need for XML configuration files.

- **Singleton Pattern (HibernateUtil Class)**: Manages the instantiation of the Hibernate connection, ensuring a single instance throughout the application.

- **CRUD Operations**: Full support for Create, Read, Update, and Delete operations on toy-related data.

## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/Dexpomar32/ToysHibernate.git
    ```

2. Configure the database connection in `HibernateConfig` class.

3. Build and run the project.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve MailBridge.
