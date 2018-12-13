import time
import os
from conans import ConanFile

class GConan(ConanFile):
    name = "LIB_G"
    version = "1.0"
    license = "MIT"
    url = "https://github.com/xingao0803/demo-multi-builds"
    settings = "os", "compiler", "build_type", "arch", "arch_build"
    os.environ["CONAN_CHANNEL"] = "stable"
    os.environ["CONAN_USERNAME"] = "jfrog"

    def requirements(self):
        self.requires("LIB_F/1.0@%s/%s" % (self.user, self.channel))

    def build(self):
        self.output.warn("Building library...")
        time.sleep(2)
