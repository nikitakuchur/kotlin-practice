package koans.collections.foldandreturn

// Return the set of products that were ordered by all customers
fun Shop.getProductsOrderedByAll(): Set<Product> =
    customers.map(Customer::getOrderedProducts)
        .reduce{ acc, products -> acc.intersect(products) }

fun Customer.getOrderedProducts(): Set<Product> =
    orders.flatMap(Order::products).toSet()
