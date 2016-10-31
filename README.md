# Magentys commons

This is a set of utility classes which were developed alongside other Magentys OS projects, but are not dependencies of those projects so should not be maintained there.

* TypedKey - Storage of typed values via typed keys, and automatic type-casted retrieval
* Memory - Managing context-based memory based on TypedKeys, allowing storage and retrieval of important state data in 'typed memory slots'
* MemoryVersioned - Extending Memory and tracking version changes across updates to any 'typed memory slot'
* DateVariant - Analogous to wave-particle duality - Date/String duality allows treatment of date fields as both strings or valid dates. Register specific string values, and comparison will account for that if not a real date. 
