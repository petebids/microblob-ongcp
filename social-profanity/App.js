var swearjar = require('swearjar')
var kafka = require('kafka-node'),
    Consumer = kafka.Consumer,
    client = new kafka.KafkaClient(),
    consumer = new Consumer(
        client,
        [
            { topic: 'LanguageDetected', partition: 0 , groupId :'profanity-detection'}
        ],
        {
            autoCommit: true
        }
    );

    consumer.on('message', function(message){
        console.log(JSON.stringify(message));
        content = message.value
        console.log(content)
        console.log(swearjar.profane(content))
        
    });

