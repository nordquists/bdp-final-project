import sys
import urllib2

PADDING_ZEROS = 12

def exhaust_url(url, destination):
    beginning = int("1" + "0" * 12)
    filedata = True

    try:
        while filedata:
            to_append = str(beginning)[1:]
            new_url = url + to_append
            print(new_url)

            filedata = urllib2.urlopen(new_url)
            datatowrite = filedata.read()
            with open(destination, 'wb') as f:
                f.write(datatowrite)

            beginning += 1
    except:
        return

def download(url_file, destination):
    with open(url_file, 'r') as urls:
        i = 1
        for url in urls:
            print("(" + str(i) + ") downloading " + url)

            exhaust_url(url, destination)

            i += 1

def main(url_file, destination):
    print('Starting download -------')
    download(url_file, destination)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python main.py <url_file> <destination>")
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])
