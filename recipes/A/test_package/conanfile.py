from conans import ConanFile
import os


channel = os.getenv("CONAN_CHANNEL", "stable")
username = os.getenv("CONAN_USERNAME", "jfrog")


class ATestConan(ConanFile):
    settings = "os", "compiler", "build_type", "arch", "arch_build"
    requires = "LIB_A/1.0@%s/%s" % (username, channel)

    def test(self):
        self.output.info("Test OK!")
