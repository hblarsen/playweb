import models.Product.ProductConfiguration
import org.specs2.mutable.Specification

/**
  * Created by HEBL on 03-11-2016.
  */
class TestProductConfiguration extends Specification{

  "Empty Configuration" should {

    "contains no features" in {

      val c = new ProductConfiguration("Test")
      c.features.contains("X") must beFalse
    }

    "allows features to be added" in {

      val c = new ProductConfiguration("Test")
      c.features += ("OS" -> "OSX")
      c.features.contains("OS") must beTrue
    }
  }


}

