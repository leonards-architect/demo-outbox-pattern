## mysql
```roomsql
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATETIME NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders_outbox (
    event_id VARCHAR(256) PRIMARY KEY,
    aggregate_id INT NOT NULL,
    aggregate_type VARCHAR(255) NOT NULL DEFAULT 'order',
    event_type VARCHAR(50) NOT NULL,
    payload TEXT NOT NULL,
    event_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    published_date TIMESTAMP
);
```

## Debezium

```bash
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{
  "name": "demo-sample-connector",
  "config": {
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",
    "database.hostname": "mysql",
    "database.port": "3306",
    "database.user": "debezium",
    "database.password": "dbz",
    "database.server.id": "223344",
    "database.server.name": "dbserver1",
    "database.include.list": "sample",
    "database.history.kafka.bootstrap.servers": "kafka:29092",
    "database.history.kafka.topic": "dbhistory.fullfillment",
    "topic.prefix": "sampledb."
  }
}'
```