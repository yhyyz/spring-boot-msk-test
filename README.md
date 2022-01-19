## spring-boot-msk-test
#### req
```shell
curl -X POST -F 'name=customer' http://localhost:9099/message
```
#### spring
```shell
nohup java -jar ./demo-0.0.1-SNAPSHOT.jar --spring.config.location=/home/ec2-user/spring/conf/application.yml > logs 2>&1 &
```
####  kill
```shell
ps axu|grep  demo-0.0.1|grep -v grep |gawk '{print $2}'|xargs -n1 kill -9

```
#### ab
```shell
cat -> post.txt << EOF
name=customer
EOF
ab -n 1 -c 1 -p 'post.txt' -T 'application/x-www-form-urlencoded' 'http://localhost:9099/message'
ab -n 200000  -c 1000 -p 'post.txt' -T 'application/x-www-form-urlencoded' 'http://localhost:9099/message'
```

