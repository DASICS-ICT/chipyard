CONFIG=MinimalConfig
XS_GEN_DIR=$(abspath ./xs-gen)
PRE_PROCESS_DIR=$(abspath ./src/main/resources)

all:
	make -C $(XS_GEN_DIR) CONFIG=$(CONFIG)
	cp -r $(XS_GEN_DIR)/build .
	python3 $(PRE_PROCESS_DIR)/renamer.py
	make -C $(PRE_PROCESS_DIR)
    mv $(PRE_PROCESS_DIR)/*.preprocessed.v build

clean:
	rm -rf build
	make -C $(XS_GEN_DIR) clean

.PHONY: all clean
