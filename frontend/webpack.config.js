const path = require('path');
const {TsconfigPathsPlugin} = require("tsconfig-paths-webpack-plugin");
const FileManagerPlugin = require("filemanager-webpack-plugin");

module.exports = {
    mode: 'production',
    entry: {
        cart: './src/pages/checkout/checkout.tsx',
        store: './src/pages/store/store.tsx',
        product: './src/pages/product/product.tsx',
        orders: './src/pages/orders/orders.tsx'
    },
    watchOptions: {
        aggregateTimeout: 25000,
        poll: 30000,
        ignored: ['**/node_modules', '/node_modules/', 'node_modules']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],

    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
        plugins: [
            new TsconfigPathsPlugin()
        ]
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    plugins: [
        new FileManagerPlugin({
            events: {
                onEnd: {
                    copy: [
                        {source: './dist', destination: '../target/classes/static/javascript'}
                    ]
                }
            }
        })
    ]
};