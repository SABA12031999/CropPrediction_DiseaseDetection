# Usage: python app.py

import flask
import werkzeug

from flask import jsonify
from flask import Flask, render_template, request, redirect, url_for
from werkzeug.utils import secure_filename
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array
from tensorflow.keras.models import  load_model
from werkzeug.middleware.shared_data import SharedDataMiddleware
import numpy as np 
from tensorflow.keras.preprocessing import image
import tensorflow as tf

app = flask.Flask(__name__)

loaded_model = load_model('mymodel')


def correctclass(result):
    if result[0] == 0:
        prediction = 'Bacterial leaf blight'
    elif result[0] == 1:
        prediction = 'Brown spot'
    else:
        prediction = 'Leaf smut'
    return prediction



@app.route('/', methods=['GET', 'POST'])
def handle_request():
    imagefile = flask.request.files['image']
    filename = werkzeug.utils.secure_filename(imagefile.filename)
    print("\nReceived image File name : " + imagefile.filename)
    imagefile.save(filename)

    # loading image and (change into array)
    img = image.load_img(filename, target_size=(256, 256))
    img = image.img_to_array(img)
    img = np.expand_dims(img, axis=0)  # (1,256,256)
    

    predicted_label = loaded_model.predict_classes(img)
    print(predicted_label)

    return jsonify(classes=str(correctclass(predicted_label)), status=200)


app.run(host="0.0.0.0", port=5000, debug=False)
