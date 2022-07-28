import 'package:flutter/material.dart';
import 'package:home/graphql/client.dart';
import 'package:home/screens/dashboard.dart';

void main() async {
  var app = await initGraphql(MyApp());

  runApp(app);
}

class MyApp extends StatelessWidget {
  MyApp({Key? key}) : super(key: key);
  final ThemeData theme = ThemeData(
    colorScheme: ColorScheme.light(
      primary: Colors.green[900]!,
      secondary: Colors.blueAccent[700]!,
    ),
    elevatedButtonTheme: ElevatedButtonThemeData(
      style: ButtonStyle(
        backgroundColor: MaterialStateColor.resolveWith(
          (states) => Colors.blueAccent[700]!,
        ),
      ),
    ),
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: theme,
      home: const Dashboard(),
    );
  }
}

// class Home extends StatelessWidget {
//   const Home({Key? key}) : super(key: key);

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(
//           title: const Text('Home'),
//         ),
//         body: Query(
//           options: QueryOptions(
//               document: gql(findUserGraphQL), errorPolicy: ErrorPolicy.all),
//           builder: (QueryResult result, {fetchMore, refetch}) {
//             if (result.hasException) {
//               return Text(result.exception.toString());
//             }

//             if (result.isLoading) {
//               return const Center(
//                 child: CircularProgressIndicator(),
//               );
//             }

//             final Map<String, dynamic>? user = result.data?['findUserByI'];

//             if (user != null) {
//               debugPrint(user.toString());
//             }

//             return const Text("sasd");
//           },
//         ));
//   }
// }
