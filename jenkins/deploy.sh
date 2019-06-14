echo "starting awesome java spring docker deployment script"

echo "stopping old container"
docker stop kdm-backend

echo "removing old container"
docker rm kdm-backend

echo "building new container"
docker build -t kdm-backend .

echo "starting new container"

JENKINS_NODE_COOKIE=dontkillme docker run \
  --name kdm-backend \
  -p 8083:8083 \
  --network=docker-network \
  --rm \
  -d  \
  kdm-backend
