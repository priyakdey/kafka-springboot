# Create a Kafka Topic

```bash
$ kafka-topics.sh --create --topic <topic_name> --partitions <partition count> --replicatation-factor <replication factor> --bootstrap-server <comma separated list of bootstrap server>
```

# List all topics

```bash
$ kafka-topics.sh --list --bootstrap-server <comma separated lisf of servers>
```

# Describe topics

```bash
$ kafka-topics.sh --describe --bootstrap-server <comma separated list of servers>
```


**NOTE**: `--bootstrap.server` accepts a list of comma separated bootstrap 
servers/brokers. It is best to provide a couple. This is because even with replication
factor, the initial command is issued on the bootstrap servers. If one is unavailable
it will fail, so its best to provide atleast 2.