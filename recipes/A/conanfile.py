import time
from conans import ConanFile

class Conan(ConanFile):
    name = "LIB_A"
    version = "1.0"
    license = "MIT"
    url = "https://github.com/xingao0803/demo-multi-builds"
    settings = "os", "compiler", "build_type", "arch"

    def build(self):
        self.output.warn("Building library...")
        time.sleep(3)

