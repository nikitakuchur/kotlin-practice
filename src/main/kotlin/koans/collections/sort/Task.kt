package koans.collections.sort

// Return a list of customers, sorted in the descending by number of orders they have made
fun Shop.getCustomersSortedByOrders(): List<Customer> =
    customers.sortedByDescending { customer -> customer.orders.size }
