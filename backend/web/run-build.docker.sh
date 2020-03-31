TAG=latest

if [ ! -z "$1" ]; then
	TAG="$1"
fi
echo "TAG: $TAG"

docker build -t kcrm-java-web:${TAG} .
