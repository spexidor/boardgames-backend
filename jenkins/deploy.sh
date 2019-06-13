echo "starting awesome java spring docker deployment script"
echo "start by building new backend java image"

docker stop kdm-backend

docker build -t kdm-backend .

echo "start new rest container"

JENKINS_NODE_COOKIE=dontkillme docker run \
  --name kdm-backend \
  -p 8083:8083 \
  --network=docker-network \
  --rm \
  -d  \
  kdm-backend
