import requests

f = open("loremipsum.txt", "r")
content = f.read()

f.close()

for sentence in content.split("."):
    body = {}
    body["content"] = sentence
    response = requests.post("http://localhost:8080/posts", json=body)
    print(response)



