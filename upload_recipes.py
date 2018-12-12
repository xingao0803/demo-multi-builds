import os
import sys

for lib in ["A", "B", "C", "D", "F", "G"]:
    os.system('cd recipes/%s && conan create -pr ../../profiles/32bits . "LIB_%s/1.0@jfrog/stable" && conan upload "LIB_%s/1.0@jfrog/stable" -r %s -c --all' % (lib, lib, lib, sys.argv[1]))
    os.system('cd recipes/%s && conan create -pr ../../profiles/64bits . "LIB_%s/1.0@jfrog/stable" && conan upload "LIB_%s/1.0@jfrog/stable" -r %s -c --all' % (lib, lib, lib, sys.argv[1]))
