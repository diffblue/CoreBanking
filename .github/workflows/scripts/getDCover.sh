# Downloads and unzips dcover from a release URL

# The release URL
RELEASE_URL=$1

mkdir dcover
cd dcover
wget -c "$RELEASE_URL" -O dcover.zip -q
unzip dcover.zip
cd ..
