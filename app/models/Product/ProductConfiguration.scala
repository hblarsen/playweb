

package models.Product

import scala.collection.mutable

// This class should represent the actual product configuration as selected by the user
// TODO: Add methods for adding and setting features

class ProductConfiguration(name: String) {

  val features = mutable.Map.empty[String, String]

}
