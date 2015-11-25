## SolrFuncPrettify - Prettify your function query
Prettify your solr function query.

### Build
Build requires `sbt`. If you haven't installed, please refer [Installing sbt](http://www.scala-sbt.org/release/tutorial/Setup.html). 

```
$ sbt assembly
```

After minutes of waiting for compiling, you will get jar. 

### Usage

```
$ cd target/scala-2.XX // XX is a version, and depends on your environment
$ java -jar solrfunc-prettify-assembly-0.1.0-SNAPSHOT.jar
Urquell% java -jar target/scala-2.10/solrfunc-prettify-assembly-0.1-SNAPSHOT.jar
Print Solr function more pretty way
...
```
If you get usage explanation like above, they it's working fine.

### Example
```
$ echo "div(def(weight, 63), product(def(tall, 170),def(tall, 170)))" | scala SolrFuncPrettify -
div(
  def(
    weight,
    63
  ),
  product(
    def(
      tall,
      170
    ),
    def(
      tall,
      170
    )
  )
)

```

