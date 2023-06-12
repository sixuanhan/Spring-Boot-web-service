create TABLE users.user(
        id BigInt NOT NULL AUTO_INCREMENT,
        email character varying(255) NOT NULL,
        first_name character varying(255) NOT NULL,
        last_name character varying(255) NOT NULL,
        address character varying(255)  NULL,
        password character varying(255)  NULL,
        PRIMARY KEY (id)
+);