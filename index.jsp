<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Agency - Your Gateway to Adventure</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <jsp:include page="/jsp/header.jsp" />

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <div class="hero-content">
                <h1>Explore the World with Us</h1>
                <p>Discover amazing destinations and create unforgettable memories</p>
                <a href="destinations" class="btn btn-primary btn-lg">Browse Destinations</a>
            </div>
        </div>
    </section>

    <!-- Featured Destinations -->
    <section class="featured-destinations">
        <div class="container">
            <h2 class="text-center mb-4">Featured Destinations</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card destination-card">
                        <img src="images/paris.jpg" class="card-img-top" alt="Paris">
                        <div class="card-body">
                            <h5 class="card-title">Paris, France</h5>
                            <p class="card-text">Experience the magic of Paris with iconic landmarks and romantic cafes</p>
                            <a href="destinations? action=detail&id=1" class="btn btn-sm btn-primary">View Details</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card destination-card">
                        <img src="images/tokyo.jpg" class="card-img-top" alt="Tokyo">
                        <div class="card-body">
                            <h5 class="card-title">Tokyo, Japan</h5>
                            <p class="card-text">Explore the vibrant culture and modern technology of Tokyo</p>
                            <a href="destinations?action=detail&id=2" class="btn btn-sm btn-primary">View Details</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card destination-card">
                        <img src="images/bali.jpg" class="card-img-top" alt="Bali">
                        <div class="card-body">
                            <h5 class="card-title">Bali, Indonesia</h5>
                            <p class="card-text">Tropical paradise with beautiful beaches and ancient temples</p>
                            <a href="destinations?action=detail&id=4" class="btn btn-sm btn-primary">View Details</a>
                        </div>
