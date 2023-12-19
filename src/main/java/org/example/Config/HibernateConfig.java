package org.example.Config;

import org.hibernate.cfg.Configuration;

public class HibernateConfig extends Configuration {
    public HibernateConfig() {
        setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        setProperty("hibernate.connection.url", "jdbc:mysql://hedgebanan.beget.tech:3306/hedgebanan_toys?noAccessToProcedureBodies=true");
        setProperty("hibernate.connection.username", "hedgebanan_toys");
        setProperty("hibernate.connection.password", "ToysDB123");

        setProperty("hibernate.show_sql", "false");
        setProperty("hibernate.current_session_context_class", "thread");
        setProperty("hibernate.hbm2ddl.auto", "update");
        setProperty("hibernate.connection.pool_size", "1");
        setProperty("hibernate.dbcp.initialSize", "1");
        setProperty("hibernate.dbcp.maxTotal", "1");
        setProperty("hibernate.dbcp.maxIdle", "1");
        setProperty("hibernate.dbcp.minIdle", "1");

        addAnnotatedClass(org.example.Models.Categorii.class);
        addAnnotatedClass(org.example.Models.Jucarii.class);
        addAnnotatedClass(org.example.Models.Papusile.class);
        addAnnotatedClass(org.example.Models.Vanzari.class);
    }
}