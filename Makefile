.PHONY: setup,teardown,run

setup:
	docker-compose -f ./cluster.yaml up -d

teardown:
	docker-compose -f ./cluster.yaml down

run:
	./producer/gradlew -b ./producer/build.gradle.kts bootRun
