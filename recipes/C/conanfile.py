import time
from conans import ConanFile

class CConan(ConanFile):
    name = "LIB_C"
    version = "1.0"
    license = "MIT"
    url = "https://github.com/xingao0803/demo-multi-builds"
    settings = "os", "compiler", "build_type", "arch", "arch_build"

    def build(self):
        self.output.warn("Building library...")
        time.sleep(2)
