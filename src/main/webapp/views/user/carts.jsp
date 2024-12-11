<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Shopping Cart</title>
  <!-- Add your styles and libraries -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
</head>
<body>

<!-- Hero Section -->
<div class="hero">
  <div class="container">
    <div class="row justify-content-between">
      <div class="col-lg-5">
        <div class="intro-excerpt">
          <h1>Cart</h1>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Cart Section -->
<div class="untree_co-section before-footer-section">
  <div class="container">
    <div class="row mb-5">
      <form class="col-md-12" method="post" action="${pageContext.request.contextPath}/user/cart/update">
        <div class="site-blocks-table">
          <table class="table">
            <thead>
              <tr>
                <th class="product-thumbnail">Image</th>
                <th class="product-name">Product</th>
                <th class="product-price">Price</th>
                <th class="product-quantity">Quantity</th>
                <th class="product-total">Total</th>
                <th class="product-remove">Remove</th>
              </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${cart.items == null || cart.items.isEmpty()}">
                  <tr>
                    <td colspan="6" class="text-center">Your cart is empty.</td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <c:forEach items="${cart.items}" var="item">
                    <tr>
                      <td class="product-thumbnail">
                        <img src="${item.productImage}" alt="Image" class="img-fluid" style="max-width: 100px;">
                      </td>
                      <td class="product-name">
                        <h2 class="h5 text-black">${item.productName}</h2>
                      </td>
                      <td>${item.price} VNĐ</td>
                      <td>
                        <div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
                          <input type="number" class="form-control text-center quantity-amount" name="quantities[${item.productId}]" value="${item.quantity}" min="1">
                        </div>
                      </td>
                      <td>${item.price * item.quantity} VNĐ</td>
                      <td>
                        <a href="${pageContext.request.contextPath}/user/cart/remove?productId=${item.productId}" class="btn btn-black btn-sm">X</a>
                      </td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </tbody>
          </table>
        </div>
        <div class="row mb-5">
          <div class="col-md-6 mb-3 mb-md-0">
            <button type="submit" class="btn btn-black btn-sm btn-block">Update Cart</button>
          </div>
          <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/shop.html" class="btn btn-outline-black btn-sm btn-block">Continue Shopping</a>
          </div>
        </div>
      </form>
    </div>

    <div class="row">
      <div class="col-md-6">
        <label class="text-black h4">Coupon</label>
        <p>Enter your coupon code if you have one.</p>
        <div class="row">
          <div class="col-md-8 mb-3 mb-md-0">
            <input type="text" class="form-control py-3" id="coupon" placeholder="Coupon Code">
          </div>
          <div class="col-md-4">
            <button class="btn btn-black">Apply Coupon</button>
          </div>
        </div>
      </div>
      <div class="col-md-6 pl-5">
        <div class="row justify-content-end">
          <div class="col-md-7">
            <div class="row">
              <div class="col-md-12 text-right border-bottom mb-5">
                <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-6">
                <span class="text-black">Subtotal</span>
              </div>
              <div class="col-md-6 text-right">
                <strong class="text-black">${cart.totalPrice} VNĐ</strong>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/checkout.html" class="btn btn-black btn-lg py-3 btn-block">Proceed To Checkout</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>

<!-- Include JavaScript -->
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/custom.js"></script>
</body>
</html>
