import time
import os
from conans import ConanFile

class BConan(ConanFile):
    name = "LIB_B"
    version = "1.0"
    license = "MIT"
    url = "https://github.com/xingao0803/demo-multi-builds"
    settings = "os", "compiler", "build_type", "arch", "arch_build"
    os.environ["CONAN_CHANNEL"] = "stable"
    os.environ["CONAN_USERNAME"] = "jfrog"

    def requirements(self):
        self.requires("LIB_A/1.0@%s/%s" % (self.user, self.channel))

    def build(self):
        self.output.warn("Building library...")
        time.sleep(2)
