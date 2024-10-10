#########################################################################################
# pre-process xiangshan into a single blackbox file
#########################################################################################

XS_TOP_DIR := $(patsubst %/,%,$(dir $(abspath $(lastword $(MAKEFILE_LIST)))))

# name of output pre-processed verilog file
PREPROC_VERILOG = $(XS_TOP_DIR)/src/main/resources/xiangshan.preprocessed.v

.PHONY: default $(PREPROC_VERILOG) init clean
default: $(PREPROC_VERILOG)

#########################################################################################
# includes and vsrcs
#########################################################################################

XS_GEN_DIR=$(XS_TOP_DIR)/xs-gen
XS_GEN_CONFIG=MinimalConfig

lookup_srcs = $(shell find -L $(1)/ -name target -prune -o -iname "*.$(2)" -print 2> /dev/null)

VLOG_DIR = $(XS_TOP_DIR)/src/main/resources/vsrc/$(NVDLA_TYPE)

ALL_VSRCS = \
	$(XS_GEN_DIR)/build/array_ext.v \
	$(XS_GEN_DIR)/build/array_*_ext.v \
	$(XS_GEN_DIR)/build/Difftest*.v \
	$(XS_GEN_DIR)/build/XSTop*.v 

INC_DIRS = $(sort $(dir $(call lookup_srcs,$(VLOG_DIR)/vmod,vh)))

#########################################################################################
# pre-process using custom script to replace the includes (but leave rest unaffected)
#########################################################################################

PREPROC_SCRIPT = $(XS_TOP_DIR)/../../scripts/insert-includes.py
#RENAMING_SCRIPT = $(XS_TOP_DIR)/renamer.py

init:
	git submodule update --depth 1 --init $(XS_GEN_DIR)
	make -C $(XS_GEN_DIR) init

$(PREPROC_VERILOG): 
	make -C $(XS_GEN_DIR) CONFIG=$(XS_GEN_CONFIG) -j16
	python3 $(XS_TOP_DIR)/renamer.py
	mkdir -p $(dir $(PREPROC_VERILOG))
	cat $(ALL_VSRCS) > combined.v
	python3 $(PREPROC_SCRIPT) combined.v $@ $(INC_DIRS)
	rm -rf combined.v

clean:
	make -C $(XS_GEN_DIR) clean
	rm -rf $(XS_TOP_DIR)/src/main/resources/*.preprocessed.v
