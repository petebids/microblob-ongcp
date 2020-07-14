## Resume project - CQRS 

demonstrate ideas around scale , CQRS, kafka consumer groups

posts should be immediatley available, but rated lower without enrichment (profanity, language detection )
    -see if this can be achieved by sorting ? 

coreography pattern - 

Services
    - Rest api - writes new posts to kafka as PostCreated Events
    - persistence container 
        - consumes PostCreated &  idempotently writes  to database & tranacitonaly emits PostPersisted 
        - consumes LanguageDetected ? 
        -consumes PrfanityChecked 
    - language detection - consumes PostPersisted events and adds a langauge detected flag to persistent state - emits LanguageDetected ? 
    - profanity detection - consumes LanguageDetected & creates either PrfanityChecked woth found boolean
    
or Saga ? 


front end 
    react SPA create post and newsfeed - update by polling or maybe wwebsockest ? 