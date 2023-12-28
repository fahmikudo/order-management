# order-platform
    
    CURL Create Order:
    
    curl -X 'POST' \
    'http://localhost:10000/platform/create-order' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "userId": 1,
    "orderItems": [
        {
            "productName": "Laptop",
            "productCategory": "Electronics",
            "productAmount": 2,
            "productPrice": 1000,
            "productId": 101,
            "productDiscountCode": "DISC10",
            "productTax": 50,
            "productDiscount": 100
        },
        {
            "productName": "Book",
            "productCategory": "Education",
            "productAmount": 3,
            "productPrice": 20,
            "productId": 102,
            "productDiscountCode": "DISC5",
            "productTax": 3,
            "productDiscount": 5
        },
        {
            "productName": "Headphones",
            "productCategory": "Electronics",
            "productAmount": 1,
            "productPrice": 50,
            "productId": 103,
            "productDiscountCode": "NO_DISCOUNT",
            "productTax": 2.5,
            "productDiscount": 0
        }
    ],
    "totalPrice": 2870,
    "totalTax": 55.5,
    "totalDiscount": 105
    }
    '