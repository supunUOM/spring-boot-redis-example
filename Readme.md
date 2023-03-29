

## Annotations

    @EnableCaching  - we need to add our main application class, which let’s spring know that in this 
    		      application caching is enabled.

    @CachePut  - annotation updates the data in Redis Cache when the update is applied on the database.

    @CacheEvict  - this annotation deletes the data from the cache when the user wants  to delete the data 
                   from the database.
	
    @Cacheable annotation is used for retrieving data from the database and storing in Redis Cache.



## Important
### Define TTLs :
Time-to-live (TTL), is the time span after which your Cache will be deleting an entry. If you want to fetch data only once a minute, just guard it with a @Cacheable Annotation and set the TTL to 1 minute.

### Implement Serializable:
If you are adding an object in Redis cache then the object should implement a Serializable interface.

### Redis Cache Limits:
When cache size reaches the memory limit, old data is removed to make a place for a new one. Although Redis is very fast, it still has no limits on storing any amount of data on a 64-bit system. It can only store 3GB of data on a 32-bit system.

### Never Call Cacheable Method from the same class:

The reason is that Spring proxy the access to these methods to make the Cache Abstraction work. When you call it within the same class this Proxy mechanic is not kicking in. By this, you basically bypass your Cache and make it non-effective.


