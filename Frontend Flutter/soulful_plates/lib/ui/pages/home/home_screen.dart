import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';

import '../../../constants/app_icons.dart';
import '../../widgets/base_common_widget.dart';
import 'home_controller.dart';

// class LiveOrdersScreen extends StatefulWidget {
//   @override
//   _LiveOrdersScreenState createState() => _LiveOrdersScreenState();
// }
//
// class _LiveOrdersScreenState extends State<LiveOrdersScreen> {
//   List<dynamic> liveOrders = [];
//
//   @override
//   void initState() {
//     super.initState();
//     fetchLiveOrders();
//   }
//
//   Future<void> fetchLiveOrders() async {
//     try {
//       // Make an HTTP GET request to fetch live orders data
//       final response = await http.get(Uri.parse('YOUR_API_ENDPOINT_HERE'));
//
//       // Check if the request was successful (status code 200)
//       if (response.statusCode == 200) {
//         // Decode the JSON string into Dart objects (lists, maps)
//         final List<dynamic> data = json.decode(response.body);
//
//         // Update the state with live orders data
//         setState(() {
//           liveOrders = data;
//         });
//       } else {
//         // Handle error if the request was not successful
//         print('Failed to fetch live orders: ${response.statusCode}');
//       }
//     } catch (e) {
//       // Handle error if something went wrong with the HTTP request
//       print('Error fetching live orders: $e');
//     }
//   }

class HomeScreen extends GetView<HomeController> with BaseCommonWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Home",
          textAlign: TextAlign.center, // Center the title
        ),
        leading: SizedBox(
          width: 40, // Adjust the width as needed
          height: 40, // Adjust the height as needed
          child: Image.asset(
            AppIcons.logo,
            fit: BoxFit.contain,
          ),
        ),
        actions: [
          PopupMenuButton(
            itemBuilder: (BuildContext context) => <PopupMenuEntry>[
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.restaurant),
                  title: Text('Restaurant Details'),
                  onTap: () {
                    // Navigate to the Restaurant Details Screen
                    //Get.to(() => restaurantDetailViewRoute());
                    Get.toNamed(restaurantDetailViewRoute);
                  },
                ),
              ),
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.shopping_cart),
                  title: Text('View Cart'),
                  onTap: () {
                    // Navigate to the View Cart Screen
                    //Get.to(() => ViewCartScreen());
                    Get.toNamed(viewCartViewRoute);
                  },
                ),
              ),
            ],
          ),
        ],
      ),
      backgroundColor: Colors.white,
      body: SafeArea(
        child: ListView(children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Image.asset(
                //   AppIcons.logo,
                //   width: 90,
                // ),
                const SizedBox(height: 20), // Adjust spacing as needed
                CardOne(),
                const SizedBox(
                    height:
                        20), // Add spacing between CardOne and "Live Orders"
                Padding(
                  padding: const EdgeInsets.only(
                      left: 8.0), // Add padding for left alignment
                  child: Text(
                    'Live Orders', // Display "Live Orders"
                    style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'Cambria', // Add custom font family
                    ),
                  ),
                ),
                const SizedBox(
                    height: 10), // Add space between "Live Orders" and Card
                // Add your live orders widgets here
                // For example, you can add another Card widget
                // Card(
                //   child: Padding(
                //     padding: const EdgeInsets.all(16.0),
                //     child: Column(
                //       crossAxisAlignment: CrossAxisAlignment.start,
                //       children: [
                //         Text('Order 1'),
                //         Text('Order 2'),
                //         // Add more orders as needed
                //       ],
                //     ),
                //   ),
                // ),
                const SizedBox(height: 20),
                CardTwo(),
                const SizedBox(height: 20),
                CardThree(),
                const SizedBox(height: 20),
                CardFour(),
              ],
            ),
          ),
        ]),
      ),
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        width: double.infinity,
        height: 150,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                // Left side
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      'Total Collected',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      '1000.00 CAD',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 18,
                      ),
                    ),
                    const SizedBox(height: 12),
                    Text(
                      '54',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 18,
                      ),
                    ),
                  ],
                ),
                // Right side
                Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: <Widget>[
                    Text(
                      'Today',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'Amount',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 12),
                    Text(
                      'Number of Orders',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class CardTwo extends StatelessWidget {
  const CardTwo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        width: double.infinity,
        height: 240,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                // Icon and Time of Order section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Icon(
                      Icons
                          .local_shipping, // Use appropriate icon based on order status
                      color: Colors.green, // Adjust color based on order status
                    ),
                    Text(
                      'Time of Order',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Order ID and Items section
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Order ID',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      '2x Tortillas\n1x Paneer Bhurji\n1x Lassi\n1x Coke',
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Status of Order and Amount in CAD section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                      onPressed: () {},
                      child: Text('Out for Delivery'),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.orangeAccent, // Change color as needed
                      ),
                    ),
                    Text(
                      '\$60 CAD',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class CardThree extends StatelessWidget {
  const CardThree({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        width: double.infinity,
        height: 240,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                // Icon and Time of Order section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Icon(
                      Icons
                          .restaurant_menu, // Use appropriate icon based on order status
                      color: Colors
                          .purpleAccent, // Adjust color based on order status
                    ),
                    Text(
                      'Time of Order',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Order ID and Items section
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Order ID',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      '2x Burger\n1x Paneer Tikka\n1x Sweet Lassi',
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Status of Order and Amount in CAD section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                      onPressed: () {},
                      child: Text('Food Preparing'),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.yellow, // Change color as needed
                      ),
                    ),
                    Text(
                      '\$45 CAD',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class CardFour extends StatelessWidget {
  const CardFour({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(8),
      child: Container(
        width: double.infinity,
        height: 240,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                // Icon and Time of Order section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Icon(
                      Icons
                          .check_circle, // Use appropriate icon based on order status
                      color: Colors
                          .deepPurple, // Adjust color based on order status
                    ),
                    Text(
                      'Time of Order',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Order ID and Items section
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Order ID',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      '2x BBQ Pizza\n1x Mango Shake\n1x Lotus Biscoff Icecream',
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Status of Order and Amount in CAD section
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                      onPressed: () {},
                      child: Text('Received'),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.lightBlue, // Change color as needed
                      ),
                    ),
                    Text(
                      '\$50 CAD',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
