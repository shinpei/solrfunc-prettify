## SolrFuncPrettify - Prettify your function query
Prettify your solr function query.

### Usage

```
$ scalac solrfunc-prettify.scala
$ scala SolrFuncPrettify yourquery.txt
```

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

