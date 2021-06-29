var swearjar = require('swearjar')
var kafka = require('kafka-node'),
    Consumer = kafka.Consumer,
    client = new kafka.KafkaClient(),
    consumer = new Consumer(
        client,
        [
            {topic: 'post-created', partition: 0, groupId: 'profanity-detection'}
        ],
        {
            autoCommit: true
        }
    );
Producer = kafka.Producer
producer = new Producer(
    client
)

consumer.on('message', function (message) {
    console.log(JSON.stringify(message));
    const postCreatedEvent = JSON.parse(message.value)
    console.log("input is "+ postCreatedEvent)
    const profane = swearjar.profane(postCreatedEvent.content)
    console.log(typeof postCreatedEvent)
    const id = postCreatedEvent.id;
    console.log("id is " + id)
    const output = JSON.stringify({
        postId: id,
        profane: profane
    })
    console.log("output is " +output)

    producer.send([{
        topic: "profanity-detected-v3",
        messages: [output],

    }], function (err, data) {
        console.log(data)

    })

});

